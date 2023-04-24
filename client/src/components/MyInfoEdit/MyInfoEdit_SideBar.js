import styled from 'styled-components';

function MyInfoEdit_SideBar() {
  return (
    <>
      <Sidebar_conatiner>
        <ul>
          {menudata.map((menu, idx) => {
            return (
              <div className="title" key={idx}>
                <li>{menu.title}</li>
                <ul>
                  {menu.subtitle.map((subtitle, subIdx) => {
                    return (
                      <li className="subtitle" key={subIdx}>
                        {subtitle}
                      </li>
                    );
                  })}
                </ul>
              </div>
            );
          })}
        </ul>
      </Sidebar_conatiner>
    </>
  );
}

const Sidebar_conatiner = styled.div`
  width: 184px;
  margin: 0px 0px 32px 0px;
  .title {
    font-size: 11px;
    font-weight: 700;
    color: rgba(0, 0, 0, 0.8);
    padding: 6px 6px;
    margin: 16px 0px 0px;
    :first-child {
      margin: 0px;
    }
  }
  .subtitle {
    font-size: 13px;
    font-weight: 400;
    color: rgba(0, 0, 0, 0.6);
    padding: 6px 6px;
    margin: 6px;
    :hover {
      border-radius: 13px;
      background-color: var(--menu-hover-background);
    }
    :active {
      background-color: #f48825;
      color: white;
    }
  }

  @media (max-width: 1250px) {
    display: none;
  }
`;

export const menudata = [
  {
    title: 'PERSONAL INFORMATION',
    subtitle: ['Edit profile', 'Delete profile'],
  },
  {
    title: 'EMAIL SETTINGS',
    subtitle: [
      'Edit email settings',
      'Tag watching & ignoring',
      'Community digests',
      'Question subscriptions',
    ],
  },
  {
    title: 'SITE SETTINGS',
    subtitle: ['Preferences', 'Flair', 'Hide communities'],
  },
  {
    title: 'ACCESS',
    subtitle: ['Your Collectives', 'Your logins'],
  },
  {
    title: 'API',
    subtitle: ['Authorized applications'],
  },
];

export default MyInfoEdit_SideBar;
